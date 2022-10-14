package nl.han.oose.dea.jordan.beroepsproduct.data.utils;

import jakarta.enterprise.context.ApplicationScoped;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.ClassNotFoundException;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.FileNotFoundException;

import java.io.IOException;
import java.util.Properties;

@ApplicationScoped
public class DatabaseProperties {

    private final Properties properties;

    public DatabaseProperties() {
        this.properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
        } catch (IOException e) { throw new FileNotFoundException(e.getMessage());
        } catch (java.lang.ClassNotFoundException ex) { throw new ClassNotFoundException(ex.getMessage());
        }
    }

    public String getDBConnectionString() {
        return properties.getProperty("url");
    }
}
