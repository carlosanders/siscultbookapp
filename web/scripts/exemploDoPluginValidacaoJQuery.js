// JavaScript Document
//<script language="javascript" src="scripts/jquery.validate.js" type="text/javascript" >
        // Inicia o validador ao carregar a página
        $(function() {
            // valida o formulário
            $('#form').validate({
                // define regras para os campos
                rules: {
                    nomeCompleto: {
                        required: true,
                        minlength: 3
                    },
                    dataNascimento:{
                        required: true,
                        minlength: 3
                    },
                    emailContato: {
                        required: true,
                        email: true
                    },
                    sexo: {
                        required: true
                    }
                },
                // define messages para cada campo
                messages: {
                    nomeCompleto: {
                        required: "Digite o seu nome",
                        minLength: "O seu nome deve conter, no mínimo, 2 caracteres"
                    },
                    dataNascimento:{
                        required: "Digite a data de nascimento",
                        minLength: "O seu nome deve conter"
                    },
                    emailContato: "Preencha seu e-mail de contato",
                    sexo: "Informe seu sexo"
                }
            });
        });
