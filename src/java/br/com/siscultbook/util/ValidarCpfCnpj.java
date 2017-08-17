package br.com.siscultbook.util;

/**
 * @author Grupo CASA JEBAR:
 * Turma: 3007
 * Nomes: Carlos Anders / Windeson / Jose Elisio / Vanessa Seidel
 * Classe ValidarCpfCnpj so para implementar o sistema afim de validar um CPF ou CNPJ valido
 */
public class ValidarCpfCnpj {

    //--- Valida CPF ou CNPJ
    @SuppressWarnings("empty-statement")
    public static boolean valida_CpfCnpj(String s_aux) {

        //--- Rotina para CPF
        if (s_aux.length() == 11) {
            //--- Rotina para verificar numero vazios e iguail na sequencia de 11 digitos
            if ((s_aux == null ? "00000000000" == null : s_aux.equals("00000000000"))
                    || (s_aux == null ? "11111111111" == null : s_aux.equals("11111111111"))
                    || (s_aux == null ? "22222222222" == null : s_aux.equals("22222222222"))
                    || (s_aux == null ? "33333333333" == null : s_aux.equals("33333333333"))
                    || (s_aux == null ? "44444444444" == null : s_aux.equals("44444444444"))
                    || (s_aux == null ? "55555555555" == null : s_aux.equals("55555555555"))
                    || (s_aux == null ? "66666666666" == null : s_aux.equals("66666666666"))
                    || (s_aux == null ? "77777777777" == null : s_aux.equals("77777777777"))
                    || (s_aux == null ? "88888888888" == null : s_aux.equals("88888888888"))
                    || (s_aux == null ? "99999999999" == null : s_aux.equals("99999999999"))) {
                return false;
            } else {
                int d1, d2;
                int digito1, digito2, resto;
                int digitoCPF;
                String nDigResult;
                d1 = d2 = 0;
                digito1 = digito2 = resto = 0;
                for (int n_Count = 1; n_Count < s_aux.length() - 1; n_Count++) {
                    digitoCPF = Integer.valueOf(s_aux.substring(n_Count - 1, n_Count)).intValue();
                    //--- Multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
                    d1 = d1 + (11 - n_Count) * digitoCPF;
                    //--- Para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
                    d2 = d2 + (12 - n_Count) * digitoCPF;
                }
                ;
                //--- Primeiro resto da divis�o por 11.
                resto = (d1 % 11);
                //--- Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11 menos o resultado anterior.
                if (resto < 2) {
                    digito1 = 0;
                } else {
                    digito1 = 11 - resto;
                }
                d2 += 2 * digito1;
                //---- Segundo resto da divis�o por 11.
                resto = (d2 % 11);
                //--- Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11 menos o resultado anterior.
                if (resto < 2) {
                    digito2 = 0;
                } else {
                    digito2 = 11 - resto;
                }
                //--- Digito verificador do CPF que est� sendo validado.
                String nDigVerific = s_aux.substring(s_aux.length() - 2, s_aux.length());
                //--- Concatenando o primeiro resto com o segundo.
                nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
                //--- Comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
                return nDigVerific.equals(nDigResult);
            }

        } //--- Rotina para CNPJ
        else if (s_aux.length() == 14) {
            int soma = 0, aux, dig;
            String cnpj_calc = s_aux.substring(0, 12);
            char[] chr_cnpj = s_aux.toCharArray();
            //--- Primeira parte
            for (int i = 0; i < 4; i++) {
                if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                    soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
                }
            }
            for (int i = 0; i < 8; i++) {
                if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                    soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
                }
            }
            dig = 11 - (soma % 11);
            cnpj_calc += (dig == 10 || dig == 11)
                    ? "0" : Integer.toString(dig);
            //--- Segunda parte
            soma = 0;
            for (int i = 0; i < 5; i++) {
                if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                    soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
                }
            }
            for (int i = 0; i < 8; i++) {
                if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                    soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
                }
            }
            dig = 11 - (soma % 11);
            cnpj_calc += (dig == 10 || dig == 11)
                    ? "0" : Integer.toString(dig);
            return s_aux.equals(cnpj_calc);
        } else {
            return false;
        }
    }
}
