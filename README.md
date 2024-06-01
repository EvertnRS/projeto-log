## Projeto Log: Relatório Completo

**Introdução**
---
O Projeto Log foi desenvolvido como parte da disciplina de Programação 2 na faculdade. Este relatório tem como objetivo apresentar o processo completo de criação do projeto, desde a fase inicial de concepção até a implementação final.

**Classes e Diagrama de Classes**
---
O projeto foi estruturado em diversas classes que interagem entre si para alcançar as funcionalidades desejadas:

**Apresentação Parcial e Feedback do Professor**

Durante a fase inicial do desenvolvimento do Projeto Log, uma apresentação parcial foi realizada para o professor responsável pela disciplina. O objetivo era demonstrar o progresso do projeto e receber feedback para aprimorar o trabalho.

Após a apresentação, o professor identificou alguns pontos que necessitavam de atenção, principalmente relacionados à coerência entre as classes e às responsabilidades atribuídas a cada uma delas. 

**Reestruturação das Classes**

Com base no feedback do professor, as classes do projeto foram reestruturadas e aprimoradas. As principais mudanças realizadas foram:

* **Separação de responsabilidades:** As responsabilidades das classes foram mais claramente definidas, evitando sobreposição de funções e garantindo que cada classe se concentre em suas tarefas específicas.
* **Melhoria da coesão:** A coesão das classes foi aprimorada, garantindo que cada classe represente um conceito único e bem delimitado.
* **Diagrama de classes atualizado:** O diagrama de classes foi atualizado para refletir as mudanças realizadas nas classes, tornando-o mais preciso e informativo.

**Diagrama de Classes Antes e Depois da Reestruturação**


[Diagrama Anterior](https://lucid.app/documents/embedded/e41c0bbd-e7ea-4807-9b99-47b317af2e12)

[Diagrama Atual](https://lucid.app/documents/embedded/b08f71e5-8ca1-471b-b942-503e93cba1d9)



**Explicação detalhada das classes após o remodelamento:**
---
**Classe LogData:**
* **Responsabilidades:**
    * Ler dados do arquivo de log.
    * Armazenar informações relevantes de cada entrada de log (endereço IP, data, tipo de solicitação, status, tamanho, sistema operacional).
    * Calcular a média do tamanho das solicitações POST feitas em 2021.
    * Gerar relatórios com base em critérios específicos (recursos grandes, solicitações não respondidas, porcentagem por SO).
* **Atributos:**
    * `logDataList`: Uma lista de objetos `LogData` que armazenam as informações de cada entrada de log.
    * `IP_Adress`: Armazena o endereço IP do cliente que fez a solicitação.
    * `year`: Representa o ano em que a solicitação foi feita.
    * `month`: Representa o mês em que a solicitação foi feita.
    * `solicitation`: Captura a solicitação específica ou ação desejada pelo cliente.
    * `typeRequest`: Identifica o tipo de solicitação sendo feita (GET, POST, PUT, etc.).
    * `status`: Rastreia o status atual da solicitação (pendente, processado, erro).
    * `length`: Representa o tamanho ou duração da solicitação.
    * `OperationalSystem`: Captura o sistema operacional do cliente que fez a solicitação.
* **Métodos:**
    * `getFileList()`: Retorna a lista de objetos `LogData`.
    * `setFileList(ArrayList<LogData> logDataList)`: Define a lista de objetos `LogData`.
    * `read(String fileName)`: Lê os dados do arquivo de log especificado e armazena as informações em objetos `LogData`.
    * `makeAverage()`: Calcula a média do tamanho das solicitações POST feitas em 2021.
    * `write(String reportType)`: Gera um relatório com base no tipo de relatório especificado ("recursosGrandes", "naoRespondidos", "OS").
---
**Classe Report:**
* **Responsabilidades:**
    * Gerar relatórios em formato de texto com base nos dados do `LogData`.
* **Métodos:**
    * `makeFile(String path, ArrayList<String> lines)`: Cria um arquivo de texto e grava as linhas de texto nele.
    * `writeFeatures(ArrayList<LogData> logDataList)`: Gera um relatório chamado "recursosGrandes.txt" que identifica entradas com status de sucesso (200 a 299) e tamanho superior a 2000 bytes.
    * `writeUnanswered(ArrayList<LogData> logDataList)`: Gera um relatório chamado "naoRespondidos.txt" que foca em solicitações com códigos de erro do cliente (400 a 499) feitas em novembro de 2021.
    * `writeOs(ArrayList<LogData> logDataList)`: Gera um relatório chamado "sistemasOperacionais.txt" que analisa os sistemas operacionais utilizados nos dados de log para o ano de 2021.
---
**Classe Principal**

* **Classe Main:**
    * **Responsabilidades:**
        * Ponto de entrada do programa.
        * Lê o arquivo de log e armazena os dados em objetos `LogData`.
        * Apresenta um menu para o usuário interagir e escolher o tipo de relatório que deseja gerar ou calcular a média das solicitações POST.
        * Chama os métodos das classes `LogData` e `Report` para gerar relatórios ou realizar cálculos conforme a escolha do usuário.
---
**Fluxo de Execução**

1. O programa lê o arquivo de log especificado (`access.log`) e armazena as informações em objetos `LogData`.
2. O usuário é apresentado a um menu com opções para gerar diferentes tipos de relatórios ou calcular a média das solicitações POST.
3. Com base na escolha do usuário, o programa chama os métodos das classes `LogData` e `Report` para:
    * Gerar um relatório de "recursos grandes respondidos" (se opção 1).
    * Gerar um relatório de "não respondidos do mês de Novembro" (se opção 2).
    * Gerar um relatório com as porcentagens das requisições por cada tipo de Sistema Operacional (se opção 3).
    * Calcular a Média das requisições do tipo POST (se opção 4).

## Autores

- [Everton Rodrigues](https://github.com/EvertnRS/)
- [Kaio Viana](https://github.com/ok-kioo)
- [Thulio Aleixo]()
