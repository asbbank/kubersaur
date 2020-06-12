package mustachejava;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.FileWriter;
import java.io.IOException;

public class Pom {

    String microserviceName(){
        return "account";
    }

    String organisationName(){
        return "engagement";
    }


    public static void main(String[] args) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("code/service1/service/pom.mustache");
        mustache.execute(new FileWriter("pom.out"), new Pom()).flush();
    }
}
