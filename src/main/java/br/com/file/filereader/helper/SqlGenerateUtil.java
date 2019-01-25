package br.com.file.filereader.helper;

/**
 * Classe auxiliar para geracao do SQL.
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 14:11:00
 */
public class SqlGenerateUtil {

    /**
     * Metodo auxiliar para iniciar o spool.
     *
     * @param sql       Recebimento do StringBuilder por referencia
     * @param action    String com descricao da a
     */
    public static void startSpool(StringBuilder sql, String action){

        sql.append("set serveroutput on\n");
        sql.append("prompt 'Executando script ").append(action).append("'\n");
        sql.append("spool &path/").append(action).append(".lst;\n");
        sql.append("SET TERMOUT OFF\n\n");
    }

    /**
     * Metodo auxiliar para fechamento do spool.
     *
     * @param sql   Recebimento do StringBuilder por referencia.
     */
    public static void finishSpool(StringBuilder sql){
        sql.append("SET TERMOUT ON\n");
        sql.append("prompt 'Fim da execucao do script'\n");
        sql.append("spool off;\n\n");
    }

    /**
     * Metodo para inclusao de delete e commit.
     *
     * @param sql       Recebimento do StringBuilder por referencia.
     * @param esquema   Definir o esquema da tabela
     * @param table     Definir a tabela a ser contada
     * @param auxiliar  Utilizar para definir um filtro
     */
    public static void deleteAllAndCommit(StringBuilder sql, String esquema, String table, String auxiliar) {
        sql.append("DELETE FROM ").append(esquema).append(".").append(table).append(auxiliar).append(";\n\n");
        SqlGenerateUtil.commit(sql);
    }

    /**
     * Metodo para iniciar um comando insert.
     *
     * @param sql       Recebimento do StringBuilder por referencia
     * @param esquema   Definir o esquema da tabela
     * @param table     Definir a tabela a ser contada
     * @param columns   Definiçao dos campos
     */
    public static void insertInit(StringBuilder sql, String esquema, String table, String columns) {
        sql.append("INSERT INTO ").append(esquema).append(".").append(table).append(" ");
        sql.append("(").append(columns).append(")\n");
        sql.append(" VALUES (");
    }

    /**
     * Metodo auxiliar para incluir contagem de registros inseridos
     *
     * @param sql       Recebimento do StringBuilder por referencia
     * @param esquema   Definir o esquema da tabela
     * @param table     Definir a tabela a ser contada
     * @param auxiliar  Utilizar para definir um filtro
     */
    public static void countRegistrosInseridos(StringBuilder sql, String esquema, String table, String auxiliar){
        sql.append("SELECT count(*) from ").append(esquema).append(".").append(table).append(auxiliar).append(";\n\n");
    }

    /**
     * Metodo para fechamento de um comando insert.
     *
     * @param sql   Recebimento do StringBuilder por referencia
     */
    public static void insertFinish(StringBuilder sql){
        sql.append(");\n\n");
    }

    /**
     * Metodo auxiliar para inclusao do comando commit.
     *
     * @param sql   Recebimento do StringBuilder por referencia
     */
    public static void commit(StringBuilder sql){
        sql.append("COMMIT;\n\n");
    }
}
