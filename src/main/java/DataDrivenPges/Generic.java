package DataDrivenPges;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Generic
{

    public FileInputStream properties() throws IOException {
        Properties p=new Properties();
        FileInputStream fp=new FileInputStream("src/test/resources/config.properties");
        p.load(fp);
        return  fp;
    }
}
