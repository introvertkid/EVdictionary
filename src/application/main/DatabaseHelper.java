package main;

import java.io.*;
import java.nio.file.Path;
import java.sql.*;

//todo: need to check if database is empty -> initialize new database
//todo: refactor connectToDatabase function because it automaticlly closes after connect
public class DatabaseHelper
{
    private static final String URL = "jdbc:mysql://localhost:3306/envi";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "bindz1403";

    private static Connection connection;

    //call only once when initialize new database
    public static void initializeDatabase() throws SQLException
    {
        final String INSERT_INTO = "insert into envi(id, target, definition) " + "values(?, ?, ?)";
//        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement pStatement = connection.prepareStatement(INSERT_INTO, Statement.RETURN_GENERATED_KEYS);
        Path path = Path.of("src/resources/DictionaryDatabase/test.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path))))
        {
            String line;
            int id=1;
            while((line = br.readLine()) != null)
            {
                String[] thisWord = line.split("\t");
                pStatement.setString(1, String.valueOf(id));
                pStatement.setString(2, thisWord[0]);
                pStatement.setString(3, thisWord[1]);
                pStatement.executeUpdate();
                id++;
            }
        }
        catch (IOException e)
        {
            System.err.println("Cannot read the file " + e);
        }
    }

    public static void connectToDatabase() throws SQLException
    {
        try
        {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect to database successfully");
//            initializeDatabase();
//            connection.close();
        }
        catch (SQLException e)
        {
            System.err.println("Failed to connect to database " + e.getMessage());
        }
    }

    //use only once to parse data from raw data
    public static void htmlParser()
    {
        Path path = Path.of("src/resources/DictionaryDatabase/anhviet138K.txt");
        Path out = Path.of("src/resources/DictionaryDatabase/out.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(path.toFile())))
        {
            String line;
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(String.valueOf(out))))
            {
                while ((line = br.readLine()) != null)
                {
                    int m = 0;
                    while(line.charAt(m)!='<')
                    {
                        m++;
                    }
                    String target = line.substring(0, m);
                    String definition = line.substring(m);

                    //change parse character '\t' if you want ?
                    bw.write(target + "\t" + definition);
                    bw.newLine();
                }
            }
            catch(IOException e)
            {
                System.err.println("Cannot write to file " + e.getMessage());
            }
        }
        catch(IOException e)
        {
            System.err.println("Cannot read the file " + e.getMessage());
        }
    }
}
