package com.fatec.fatura.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.fatec.fatura.model.Fatura;

class TUReq16EmissaoDaFaturaTests {
	Logger logger = LogManager.getLogger(this.getClass());
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	Fatura fatura;

	@Test
	void ct01_quando_dados_validos_fatura_nao_eh_nulo() {
		try {
			// dado que as informacoes de fatura sao validas - Entrada Correta
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("39086360009", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje
			assertNotNull(fatura);
		} catch (Exception e) {
			logger.info(">>>>>> ct01 - nao deveria falhar => " + e.getMessage());
			fail("nao deveria falhar fatura valida");

		}

	}
	@Test
	void ct02_quando_dados_invalidos_retorna_msg_erro() {
		try {
			// dado que as informacoes sao invalidas - String null
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura(null, dataVencimento, "moveis planejados", "1000.50");
			// entao retorna mensagem de erro
			
		} catch (Exception e) {
			logger.info(">>>>>> ct02 - nao deveria falhar => " + e.getMessage());
			assertEquals("CPF invalido", e.getMessage());
        }

	}
	@Test
	void ct03_quando_dados_invalidos_retorna_msg_erro() {
		try {
			// dado que as informacoes sao invalidas - String Vazio
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("", dataVencimento, "moveis planejados", "1000.50");
			// entao retorna mensagem de erro
			
		} catch (Exception e) {
			logger.info(">>>>>> ct03 - mensagem de erro => " + e.getMessage());
			assertEquals("CPF invalido", e.getMessage());
        }

	}
	@Test
	void ct04_quando_dados_invalidos_retorna_msg_erro() {
        try {
            // dado que as informacoes sao invalidas - CPF maior
            LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
            // quando confirmo a fatura
            fatura = new Fatura("390863600092", dataVencimento, "moveis planejados", "1000.50");
            // entao retorna mensagem de erro
            fail("Deveria Falhar");
        } catch (Exception e) {
            logger.info(">>>>>> ct04 - mensagem de erro => " + e.getMessage());
            assertEquals("CPF invalido", e.getMessage());
        }
 
 
    }
	@Test
    void ct05_quando_dados_invalidos_retorna_msg_erro() {
       try {
           // dado que as informacoes sao invalidas - Filtragem de caracteres
           LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
           // quando confirmo a fatura
           fatura = new Fatura("123.456.789-00", dataVencimento, "moveis planejados", "1000.50");
           fail("Deveria falhar");
           // entao retorna mensagem de erro
           
       } catch (Exception e) {
           logger.info(">>>>>> ct05 - mensagem de erro => " + e.getMessage());
           assertEquals("CPF invalido", e.getMessage());
       }
 
   }
   @Test
    void ct06_quando_dados_invalidos_retorna_msg_erro() {
       try {
           // dado que as informacoes sao invalidas - String texto, não CPF
           LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
           // quando confirmo a fatura
           fatura = new Fatura("MeuDeusdoCeu", dataVencimento, "moveis planejados", "1000.50");
           fail("Deveria falhar");
           // entao retorna mensagem de erro
           
       } catch (Exception e) {
           logger.info(">>>>>> ct06 - mensagem de erro => " + e.getMessage());
           assertEquals("CPF invalido", e.getMessage());
       }
 
   }
   @Test
    void ct07_quando_dados_invalidos_retorna_msg_erro() {
       try {
           // dado que as informacoes sao invalidas - Caracteres especiais
           LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
           // quando confirmo a fatura
           fatura = new Fatura("á123456789-10%!@", dataVencimento, "moveis planejados", "1000.50");
           fail("Deveria falhar");
           // entao retorna mensagem de erro
           
       } catch (Exception e) {
           logger.info(">>>>>> ct07 - mensagem de erro => " + e.getMessage());
           assertEquals("CPF invalido", e.getMessage());
       }
 
   }
}
