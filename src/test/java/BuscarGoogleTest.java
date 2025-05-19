import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.*;
import org.testng.Assert;

/****************************************/
// Historia de Usuario: Como usuario quiero buscar terminos en Google
//
// Prueba de Aceptacion: Verificar que el termino de busqueda sea mostrado en la pagina de resultados
//
// 1. Ingresar a la pagina de Google
// 2. En el campo de texto, escribir un termino de busqueda i.e. Universidad Catolica Boliviana
// 3. Presionar la tecla Enter
// 
// Resultado Esperado: El termino de busqueda, i.e. Universidad Catolica Boliviana, 
// debe mostrarse en la pagina de resultados
/****************************************/

public class BuscarGoogleTest {
    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception{
        
    	String path = "/Users/gustavo/apps/chromedriver-mac-x64_119/chromedriver119";
        
        System.setProperty("webdriver.chrome.driver", path);
        
        WebDriverManager.chromedriver().setup();
        
        driver = new ChromeDriver();
        
        
    }
    
    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
    
    @Test
    public void paginaPrincipalGoogle(){
        
        /**************Preparacion de la prueba***********/
    	
    	//1. Ingresar a la pagina de Google
        String googleUrl = "https://www.google.com";
        driver.get(googleUrl);
        
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        } 
        
        /**************Logica de la prueba***************/
        // 2. En el campo de texto, escribir un criterio de busqueda
        
        /*Capturar el campo de busqueda*/
        
        WebElement campoBusqueda = driver.findElement(By.name("q"));

        /*Escribir el termino de busqueda*/
        campoBusqueda.sendKeys("Universidad Catolica Boliviana");
        
        try{
            TimeUnit.SECONDS.sleep(7);
        }
        catch(InterruptedException e){
        	e.printStackTrace();
        }    
        
        //3. Presionar la tecla Enter
        campoBusqueda.submit();
        
        
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        } 
        
        /************Verificacion de la situacion esperada - Assert***************/
        
        WebElement resultado = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/table/tbody/tr[1]/td/div/div/div/div/h3/a"));


        String label = resultado.getText();
        System.out.println("Texto del resultado:: "+label);
        
        
        Assert.assertEquals(label,"Universidad Católica Boliviana");
    }
    
   
   
    
    
    
    
    /*
     
        
       
        
        
        

        
        
        
        try{
            TimeUnit.SECONDS.sleep(7);
        }
        catch(InterruptedException e){
        	e.printStackTrace();
        }    
        
        
       

      */
    
}







