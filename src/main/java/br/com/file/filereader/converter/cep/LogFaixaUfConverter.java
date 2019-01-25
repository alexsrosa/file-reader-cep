package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogFaixaUf;


/**
 * Classe de conversão de layout do arquivo de LOG_FAIXA_UF de CEP.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:00:00
 */
public class LogFaixaUfConverter implements Converter<LogFaixaUf, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_FAIXA_UF";
    private static final String columns = "UFE_SG, UFE_CEP_INI, UFE_CEP_FIM, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogFaixaUf convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogFaixaUf logFaixaUf = new LogFaixaUf();

        logFaixaUf.setUfeSg(getString(splited, 0));
        logFaixaUf.setUfeCepIni(getString(splited, 1));
        logFaixaUf.setUfeCepFim(getString(splited, 2));

        return logFaixaUf;
    }

    @Override
    public StringBuilder generateSQL(LogFaixaUf logFaixauf) {

        StringBuilder sql = new StringBuilder();

        if (contador == 0) SqlGenerateUtil.deleteAllAndCommit(sql, esquema, table, "");

        contador++;

        if (!logFaixauf.getUfeSg().equals("")) {

            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append("'").append(escapeSpecial(logFaixauf.getUfeSg())).append("', ");
            sql.append("'").append(escapeSpecial(logFaixauf.getUfeCepIni())).append("', ");
            sql.append("'").append(escapeSpecial(logFaixauf.getUfeCepFim())).append("', ");
            sql.append("USER, ");
            sql.append("current_timestamp");

            SqlGenerateUtil.insertFinish(sql);
        }

        if ((contador % numCommits) == 0) SqlGenerateUtil.commit(sql);

        return sql;
    }

    @Override
    public void countRegistros(StringBuilder stringBuilder) {
        SqlGenerateUtil.countRegistrosInseridos(stringBuilder, esquema, table, "");
    }
}
