package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		// abrir esse endere�o
		driver.navigate().to("http://localhost:8001/tasks/");

		// aguardar 10 segudos
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			// encontrar o bot�o por id na p�gina inicial e clicar
			driver.findElement(By.id("addTodo")).click();

			// encontrar primeiro campo para escrever
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			// encontrar segundo campo para escrever
			driver.findElement(By.id("dueDate")).sendKeys("20/01/2022");

			// clicar no bot�o salvar
			driver.findElement(By.id("saveButton")).click();

			// validar a mensagem que aparece na tela
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
			// fechar o brownser
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		try {
			// encontrar o bot�o por id na p�gina inicial e clicar
			driver.findElement(By.id("addTodo")).click();

			// encontrar segundo campo para escrever
			driver.findElement(By.id("dueDate")).sendKeys("20/01/2022");

			// clicar no bot�o salvar
			driver.findElement(By.id("saveButton")).click();

			// validar a mensagem que aparece na tela
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
			// fechar o brownser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
			// encontrar o bot�o por id na p�gina inicial e clicar
			driver.findElement(By.id("addTodo")).click();

			// encontrar primeiro campo para escrever
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			// clicar no bot�o salvar
			driver.findElement(By.id("saveButton")).click();

			// validar a mensagem que aparece na tela
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
			// fechar o brownser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
			// encontrar o bot�o por id na p�gina inicial e clicar
			driver.findElement(By.id("addTodo")).click();

			// encontrar primeiro campo para escrever
			driver.findElement(By.id("task")).sendKeys("Teste via selenium");

			// encontrar segundo campo para escrever
			driver.findElement(By.id("dueDate")).sendKeys("10/01/2021");

			// clicar no bot�o salvar
			driver.findElement(By.id("saveButton")).click();

			// validar a mensagem que aparece na tela
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			// fechar o brownser
			driver.quit();
		}
	}
}
