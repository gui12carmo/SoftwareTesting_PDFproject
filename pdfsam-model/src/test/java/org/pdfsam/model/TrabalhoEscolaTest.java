package org.pdfsam.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sejda.model.outline.OutlinePolicy;
import org.sejda.model.pdf.PdfVersion;
import org.sejda.model.rotation.Rotation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TRABALHO DE ESCOLA - TESTES UNITÁRIOS
 * Objetivo: Garantir a integridade das opções de configuração do PDFsam.
 * Se estes testes falharem, os menus do aplicativo aparecerão vazios ou quebrados.
 */

public class TrabalhoEscolaTest {


    // CLASSE 1: PdfVersion (Versão do Arquivo PDF)
    // Cenário: O usuário quer garantir que o PDF salvo abra em leitores específicos.
    // ==================================================================================

    @Test
    @DisplayName("1. PdfVersion: O sistema deve suportar a versão moderna 1.7")
    void testVersion17() {
        // A versão 1.7 é a mais usada hoje em dia (padrão ISO).
        // Aqui varremos a lista de versões procurando por "1_7".
        boolean existe = Arrays.stream(PdfVersion.values())
                .anyMatch(v -> v.name().contains("1_7"));

        // Se der falso, significa que o PDFsam "esqueceu" como criar PDFs modernos.
        assertTrue(existe, "Erro Crítico: O sistema não suporta PDF versão 1.7");
    }

    @Test
    @DisplayName("2. PdfVersion: O sistema deve suportar versões legado (1.4 ou 1.5)")
    void testVersionLegacy() {
        // Alguns tribunais e governos ainda usam sistemas antigos (Legacy).
        // Precisamos garantir que existe suporte para versão 1.4 ou 1.5.
        boolean existe = Arrays.stream(PdfVersion.values())
                .anyMatch(v -> v.name().contains("1_4") || v.name().contains("1_5"));

        assertTrue(existe, "O sistema deve suportar versões antigas para compatibilidade");
    }

    @Test
    @DisplayName("3. PdfVersion: A lista de versões não pode ser nula")
    void testVersionNotNull() {
        // Teste de segurança básica: A primeira versão da lista existe?
        assertNotNull(PdfVersion.values()[0]);
    }

    @Test
    @DisplayName("4. PdfVersion: Quantidade mínima de versões suportadas")
    void testVersionCount() {
        // Esperamos ver pelo menos: 1.4, 1.5, 1.6, 1.7.
        // Se tiver menos de 4, alguém apagou funcionalidades importantes.
        assertTrue(PdfVersion.values().length >= 4, "Devem existir pelo menos 4 versões de PDF listadas");
    }

    @Test
    @DisplayName("5. PdfVersion: Validação de Nomenclatura Interna")
    void testVersionNaming() {
        // Verifica se os nomes internos das versões seguem o padrão do código "VERSION_..."
        assertTrue(PdfVersion.values()[0].name().contains("VERSION"));
    }

    // CLASSE 2: OutlinePolicy (Política de Marcadores/Bookmarks)
    // Cenário: Ferramenta "Merge". O usuário junta 3 PDFs e quer manter o sumário original.
    // ==================================================================================

    @Test
    @DisplayName("6. Outline: O menu deve ter a opção 'Manter' (Retain)")
    void testOutlineRetain() {
        // Buscamos na lista de políticas se existe a opção RETAIN (Manter).
        boolean existe = Arrays.stream(OutlinePolicy.values())
                .anyMatch(p -> p.name().contains("RETAIN"));

        assertTrue(existe, "A opção vital 'Manter Marcadores' desapareceu!");
    }

    @Test
    @DisplayName("7. Outline: O menu deve ter a opção 'Descartar' (Discard)")
    void testOutlineDiscard() {
        // Buscamos se existe a opção DISCARD (Jogar fora/Descartar).
        boolean existe = Arrays.stream(OutlinePolicy.values())
                .anyMatch(p -> p.name().contains("DISCARD"));

        assertTrue(existe, "A opção 'Descartar Marcadores' desapareceu!");
    }

    @Test
    @DisplayName("8. Outline: Contagem de opções no menu")
    void testOutlineCount() {
        // O menu dropdown deve ter pelo menos 2 opções para o usuário escolher.
        assertTrue(OutlinePolicy.values().length >= 2);
    }

    @Test
    @DisplayName("9. Outline: Integridade dos dados")
    void testOutlineIntegrity() {
        // Percorre todas as opções e garante que nenhuma tem nome vazio ou é nula.
        for (OutlinePolicy p : OutlinePolicy.values()) {
            assertNotNull(p);
            assertFalse(p.name().isEmpty());
        }
    }

    @Test
    @DisplayName("10. Outline: Teste de Sanidade (A classe carregou?)")
    void testOutlineSanity() {
        // Verifica se a classe inteira foi carregada na memória do Java.
        assertNotNull(OutlinePolicy.values());
    }

    // CLASSE 3: Rotation (Matemática da Rotação)
    // Cenário: Ferramenta "Rotate". O usuário clica para girar a página.
    // ==================================================================================

    @Test
    @DisplayName("11. Rotation: Devem existir exatamente 4 ângulos possíveis")
    void testRotationCount() {
        // Um PDF só pode estar em 0, 90, 180 ou 270 graus.
        // Se houver 3 ou 5 opções, a lógica matemática está quebrada.
        assertEquals(4, Rotation.values().length);
    }

    @Test
    @DisplayName("12. Rotation: Validação do valor 90 graus")
    void testRotation90() {
        // Garante que a constante "DEGREES_90" vale numericamente o int 90.
        assertEquals(90, Rotation.DEGREES_90.getDegrees());
    }

    @Test
    @DisplayName("13. Rotation: Validação do valor 180 graus (Cabeça para baixo)")
    void testRotation180() {
        // Garante que a constante "DEGREES_180" vale numericamente o int 180.
        assertEquals(180, Rotation.DEGREES_180.getDegrees());
    }

    @Test
    @DisplayName("14. Rotation: Validação do valor 270 graus")
    void testRotation270() {
        // Garante que a constante "DEGREES_270" vale numericamente o int 270.
        assertEquals(270, Rotation.DEGREES_270.getDegrees());
    }

    @Test
    @DisplayName("15. Rotation: Validação do padrão 0 graus (Sem rotação)")
    void testRotation0() {
        // A rotação inicial deve ser sempre 0.
        assertEquals(0, Rotation.DEGREES_0.getDegrees());
    }

    /**
 * TESTES UNITÁRIOS - GABRIELLA
 * Objetivo: validar integridade de enums usados em opções/configurações do PDFsam.
 * Estes testes garantem consistência interna e evitam menus quebrados ou opções inválidas.
 */

    @Test
    @DisplayName("G1. PdfVersion: nenhuma versão pode ser nula e o nome não pode ser vazio")
    void pdfVersion_shouldNotHaveNullOrEmptyNames() {
        for (PdfVersion v : PdfVersion.values()) {
            assertNotNull(v, "Encontrou PdfVersion nulo");
            assertFalse(v.name().isBlank(), "Encontrou PdfVersion com nome vazio");
        }
    }

    @Test
    @DisplayName("G2. PdfVersion: nomenclatura deve seguir padrão VERSION_1_X (ex: VERSION_1_7)")
    void pdfVersion_namesShouldMatchExpectedPattern() {
        Pattern pattern = Pattern.compile("^VERSION_\\d_\\d$");

        for (PdfVersion v : PdfVersion.values()) {
            assertTrue(pattern.matcher(v.name()).matches(),
                    "Nome fora do padrão esperado: " + v.name());
        }
    }

    @Test
    @DisplayName("G3. Rotation: graus devem ser apenas {0, 90, 180, 270}")
    void rotation_degreesMustBeValidSet() {
        Set<Integer> valid = Set.of(0, 90, 180, 270);

        for (Rotation r : Rotation.values()) {
            assertTrue(valid.contains(r.getDegrees()),
                    "Grau inválido encontrado: " + r.getDegrees());
        }
    }

    @Test
    @DisplayName("G4. Rotation: graus não podem se repetir (unicidade)")
    void rotation_degreesMustBeUnique() {
        int[] degrees = Arrays.stream(Rotation.values())
                .mapToInt(Rotation::getDegrees)
                .toArray();

        Set<Integer> unique = new HashSet<>();
        for (int d : degrees) {
            assertTrue(unique.add(d), "Grau repetido encontrado: " + d);
        }
    }
    
}
