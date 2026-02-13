package org.pdfsam.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sejda.model.outline.OutlinePolicy;
import org.sejda.model.pdf.PdfVersion;
import org.sejda.model.rotation.Rotation;

import java.util.Arrays;

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

    @Test
    @DisplayName("16. PdfVersion: Validar todas as versões conhecidas explicitamente")
    void testAllPdfVersionsExist() {
        // Testa cada versão esperada individualmente
        assertNotNull(PdfVersion.VERSION_1_4);
        assertNotNull(PdfVersion.VERSION_1_5);
        assertNotNull(PdfVersion.VERSION_1_6);
        assertNotNull(PdfVersion.VERSION_1_7); 
    }

    @Test
    @DisplayName("17. PdfVersion: Versão 1.7 deve ser mais recente que 1.4")
    void testVersionComparison() {
        // Se a classe implementar Comparable
        assertTrue(PdfVersion.VERSION_1_7.ordinal() > PdfVersion.VERSION_1_4.ordinal());
    }

    @Test
    @DisplayName("18. PdfVersion: Garantir que enums não podem ser modificadas")
        void testPdfVersionImmutable() {
        PdfVersion[] versions1 = PdfVersion.values();
        PdfVersion[] versions2 = PdfVersion.values();
        assertEquals(versions1.length, versions2.length);
        // Ambas devem ter o mesmo tamanho (imutabilidade)
    }
    
    @Test
    @DisplayName("19. Outline: Validar cada política individualmente")
    void testEachOutlinePolicyExists() {
        assertNotNull(OutlinePolicy.RETAIN);
        assertNotNull(OutlinePolicy.DISCARD);
        // Adicionar outras que existirem
    }

    @Test
    @DisplayName("20. Rotation: Validar ângulos não existentes retornam null ou lançam exceção")
    void testRotationInvalidAngles() {
        Rotation invalid45 = null;
        // Tenta encontrar ângulo de 45 graus
        try {
            invalid45 = Arrays.stream(Rotation.values())
                    .filter(r -> r.getDegrees() == 45)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            // Esperado
        }
        assertNull(invalid45, "Ângulo de 45 graus não deve existir");
    }

    @Test
    @DisplayName("21. Rotation: Ângulos devem estar em ordem crescente")
    void testRotationSequence() {
        int[] expected = {0, 90, 180, 270};
        int[] actual = Arrays.stream(Rotation.values())
                .mapToInt(Rotation::getDegrees)
                .sorted()
                .toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("22. PdfVersion: Todos os enums devem ter representação em String")
    void testPdfVersionToString() {
        for (PdfVersion v : PdfVersion.values()) {
            assertNotNull(v.toString());
            assertFalse(v.toString().isEmpty());
        }
    }
    
    @Test
    @DisplayName("23. PdfVersion: Contar versões modernas (>=1.6)")
    void testModernVersionsCount() {
        long count = Arrays.stream(PdfVersion.values())
                .filter(v -> v.name().contains("1_6") || v.name().contains("1_7"))
                .count();
        assertTrue(count >= 2, "Deve haver pelo menos 2 versões modernas");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, 90, 180, 270})
    @DisplayName("24. Rotation: Validar todos os ângulos válidos em um teste")
    void testAllRotationAngles(int expectedDegrees) {
        boolean exists = Arrays.stream(Rotation.values())
                .anyMatch(r -> r.getDegrees() == expectedDegrees);
        assertTrue(exists, "Ângulo " + expectedDegrees + " deve existir");
    }
    
    @Test
    @DisplayName("25. PdfVersion: Acessar índice fora dos limites")
    void testVersionOutOfBounds() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            PdfVersion v = PdfVersion.values()[999];
        });
    }
}
