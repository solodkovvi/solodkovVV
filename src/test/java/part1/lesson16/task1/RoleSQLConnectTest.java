package part1.lesson16.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import part1.lesson16.task1.connector.ConnectorJDBC;
import part1.lesson16.task1.connector.ConnectorJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class RoleSQLConnectTest {
    private static final Logger logger = LogManager.getLogger();
    private ConnectorJDBC connectionManager;
    private Connection connection;
    private RoleSQLConnect roleSQLConnect;
    @Mock
    private PreparedStatement preparedStatement;

    @BeforeEach
    void setUp() {
        initMocks(this);
        logger.info("Start test Role");
        connectionManager = spy(ConnectorJDBCImpl.getInstance());
        connection = mock(Connection.class);
    }

    @Test
    void insertRole() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        doReturn(preparedStatement).when(connection).prepareStatement(RoleSQLConnect.SQL_INSERT_ROLE);
        Role role = new Role(0,"test","test");
        roleSQLConnect = new RoleSQLConnect(connectionManager);
        int result = roleSQLConnect.insertRole(role);
        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(RoleSQLConnect.SQL_INSERT_ROLE);
        verify(preparedStatement, times(1)).executeQuery();
        assertTrue(result!=-1);
    }

    @AfterEach
    void setDown() throws SQLException {
        logger.info("End test Role");
        connectionManager.getConnection().close();
    }
}