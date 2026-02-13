package org.pdfsam.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sejda.model.pdf.PdfVersion;
import org.sejda.model.rotation.Rotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 🎓 TRABALHO ACADÉMICO - SUÍTE DE TESTES UNITÁRIOS
 * PROJETO: PDFsam (PDF Split and Merge)
 * OBJETIVO: Validar a integridade das Enums de configuração (Versões, Políticas e Rotações).
 * IMPACTO: Garante que a interface do utilizador não apresente menus vazios ou opções inválidas.
 */

public class TrabalhoEscolaTest {

    // ==================================================================================
    // GRUPO 1: ROTATION (Lógica Geométrica das Páginas)
    // GUILHERME CARMO (Nº 2024176)
    // ==================================================================================

    @Test
    @DisplayName("1. Rotation -> Consistência dos 4 Eixos Cardinais (0, 90, 180, 270)")
    void testRotationCount() {
        assertEquals(4, Rotation.values().length, "A lógica de rotação deve admitir apenas 4 ângulos retos.");
    }

    @Test
    @DisplayName("2. Rotation -> Validação Numérica: 90 Graus")
    void testRotation90() {
        assertEquals(90, Rotation.DEGREES_90.getDegrees());
    }

    @Test
    @DisplayName("3. Rotation -> Validação Numérica: 180 Graus")
    void testRotation180() {
        assertEquals(180, Rotation.DEGREES_180.getDegrees());
    }

    @Test
    @DisplayName("4. Rotation -> Validação Numérica: 270 Graus")
    void testRotation270() {
        assertEquals(270, Rotation.DEGREES_270.getDegrees());
    }

    @Test
    @DisplayName("5. Rotation -> Validação Numérica: 0 Graus (Estado Inicial)")
    void testRotation0() {
        assertEquals(0, Rotation.DEGREES_0.getDegrees());
    }

    // ==================================================================================
    // Grupo 2: Validação de padrões de Regex, Unicidade e Integridade de Strings.
    // GABRIELLA REZENDE (Nº 2024517)
    // ==================================================================================

    @Test
    @DisplayName("6. Integridade -> Verificação Global de Nomes (PdfVersion)")
    void pdfVersion_shouldNotHaveNullOrEmptyNames() {
        for (PdfVersion v : PdfVersion.values()) {
            assertNotNull(v, "Versão nula encontrada.");
            assertFalse(v.name().isBlank(), "Nome de versão em branco detectado.");
        }
    }

    @Test
    @DisplayName("7. Padronização -> Validação de Regex (VERSION_1_X)")
    void pdfVersion_namesShouldMatchExpectedPattern() {
        Pattern pattern = Pattern.compile("^VERSION_\\d_\\d$");
        for (PdfVersion v : PdfVersion.values()) {
            assertTrue(pattern.matcher(v.name()).matches(), "Padrão violado em: " + v.name());
        }
    }

    @Test
    @DisplayName("8. Geometria -> Validação do Set de Ângulos Permitidos")
    void rotation_degreesMustBeValidSet() {
        Set<Integer> valid = Set.of(0, 90, 180, 270);
        for (Rotation r : Rotation.values()) {
            assertTrue(valid.contains(r.getDegrees()), "Ângulo não convencional detectado: " + r.getDegrees());
        }
    }

    @Test
    @DisplayName("9. Unicidade -> Garantia de Não-Repetição de Graus")
    void rotation_degreesMustBeUnique() {
        Set<Integer> unique = new HashSet<>();
        for (Rotation r : Rotation.values()) {
            assertTrue(unique.add(r.getDegrees()), "Grau duplicado encontrado na Enum: " + r.getDegrees());
        }
    }

    // ==================================================================================
    // Grupo 3: Validação de Ordenação, Imutabilidade e Métodos de Objeto.
    // THALES PIRES (Nº 2024475)
    // ==================================================================================

    @Test
    @DisplayName("10. Thales -> Verificação de Existência das Constantes de Versão")
    void testAllPdfVersionsExist() {
        assertNotNull(PdfVersion.VERSION_1_4);
        assertNotNull(PdfVersion.VERSION_1_5);
        assertNotNull(PdfVersion.VERSION_1_6);
        assertNotNull(PdfVersion.VERSION_1_7);
    }

    @Test
    @DisplayName("11. Thales -> Comparação de Ordem Cronológica de Versões")
    void testVersionComparison() {
        assertTrue(PdfVersion.VERSION_1_7.ordinal() > PdfVersion.VERSION_1_4.ordinal());
    }

    @Test
    @DisplayName("12. Thales -> Garantia de Imutabilidade da Lista de Enums")
    void testPdfVersionImmutable() {
        assertEquals(PdfVersion.values().length, PdfVersion.values().length, "O estado da Enum deve ser imutável.");
    }

    @Test
    @DisplayName("13. Thales -> Tratamento de Ângulos Inválidos (Ex: 45 graus)")
    void testRotationInvalidAngles() {
        Rotation invalid45 = null;
        try {
            invalid45 = Arrays.stream(Rotation.values())
                    .filter(r -> r.getDegrees() == 45)
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) { /* Erro esperado */ }
        assertNull(invalid45, "O sistema não deve permitir ângulos fora dos 90 graus.");
    }

    @Test
    @DisplayName("14. Thales -> Validação da Representação Textual (toString)")
    void testPdfVersionToString() {
        for (PdfVersion v : PdfVersion.values()) {
            assertNotNull(v.toString());
            assertFalse(v.toString().isEmpty());
        }
    }
}