import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/****************************************/
//Historia de Usuario: Como estudiante nuevo quiero encontrar el telefono de un docente
//
//Prueba de Aceptacion / Caso de Prueba   TC:125: Verificar que el directorio de la pagina de la UCB me muestre el telefono de un docente
//
//PASO 1. Ingresar a la pagina de la UCB  https://lpz.ucb.edu.bo/
//PASO 2. Hacer click en el boton Directorio
//PASO 3. Insertar el nombre del docente que estoy buscando y presionar el boton  Buscar

//Resultado Esperado: El nombre y telefono del docente debe estar visible en la tabla de resultados
/****************************************/

//Para ejecutar en la linea de comando: mvn clean compile test -Dtest=BuscadorUCBTest

public class BuscadorUCBTest {

    
    private WebDriver driver;
    
    @BeforeTest
    public void setDriver() throws Exception{
        
    	 String path = "/Users/gustavo/apps/chromedriver-mac-x64-148/chromedriver";
         
         System.setProperty("webdriver.chrome.driver", path);
         
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
        
    }
    
    @Test
    public void buscarPersonaTest(){
        
        /********** Preparacion de la prueba **********/
    	
    	//PASO 1. Ingresar a la pagina de la UCB  https://lpz.ucb.edu.bo/
        String dzoneUrl = "https://lpz.ucb.edu.bo/";
        driver.get(dzoneUrl);
        

        //Esperamos 3 segundos para que se renderice la siguiente pagina
        try{
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException e){
            e.printStackTrace();
     
        }
        System.out.println("Esperando a la pagina.....");

        /*********** Logica de la prueba***********/
        
        //PASO 2. Hacer click en el boton Directorio
        
        WebElement directorio = driver.findElement(By.xpath("//*[@id=\"et-secondary-nav\"]/li[6]/a"));
        
        System.out.println("Se muestra el texto del boton: "+directorio.getText());

        directorio.click();;
        
        //Esperamos 3 segundos para que se renderice la siguiente pagina
        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
     
        }
        
        
        //PASO 3. Insertar el nombre del docente que estoy buscando y presionar el boton  Buscar
        WebElement buscador = driver.findElement(By.xpath("//*[@id=\"tbTextoBuscar\"]"));                                
        
        buscador.sendKeys("Duran");

        try{
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        WebElement botonBuscar = driver.findElement(By.xpath("//*[@id=\"btnBuscar\"]"));                                
        
        botonBuscar.click();

        try{
            TimeUnit.SECONDS.sleep(10);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        /************Verificacion de la situacion esperada - Assert ***************/
        
        WebElement nombreDocente = driver.findElement(By.xpath("//*[@id=\"gvAdministrativos\"]/tbody/tr[2]/td[1]"));

        boolean estaPresente = nombreDocente.getText().contains("DURAN");
        Assert.assertEquals(true, estaPresente);
        
    }
    
    
    
    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
    
    
}
