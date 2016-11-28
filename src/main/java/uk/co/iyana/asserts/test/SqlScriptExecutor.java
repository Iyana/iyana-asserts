/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.iyana.asserts.test;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

/**
 *
 * @author fgyara
 */
public class SqlScriptExecutor {
    
    
    public static void execute (String jndiName, String resourceUrl) throws NamingException, SQLException {
        Context context = new InitialContext();
        DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/" + jndiName);        
        
        Resource resource = new ClassPathResource(resourceUrl);
        ScriptUtils.executeSqlScript( ds.getConnection(), resource);
    }
}
