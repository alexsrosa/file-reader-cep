package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogFaixaLocalidade;


/**
 * Classe de conversão de layout do arquivo de LOG_FAIXA_LOCALIDADE de CEP.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:00:00
 */
public class LogFaixaLocalidadeConverter implements Converter<LogFaixaLocalidade, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_FAIXA_LOCALIDADE";
    private static final String columns = "LOC_NU, LOC_CEP_INI, LOC_CEP_FIM, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogFaixaLocalidade convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogFaixaLocalidade logFaixaLocalidade = new LogFaixaLocalidade();

        logFaixaLocalidade.setLocNu(getNumber(splited, 0));
        logFaixaLocalidade.setLocCepIni(getString(splited, 1));
        logFaixaLocalidade.setLocCepFim(getString(splited, 2));

        return logFaixaLocalidade;
    }

    @Override
    public StringBuilder generateSQL(LogFaixaLocalidade logFaixaLocalidade) {

        StringBuilder sql = new StringBuilder();

        if (contador == 0) SqlGenerateUtil.deleteAllAndCommit(sql, esquema, table, "");

        contador++;

        if (logFaixaLocalidade.getLocNu() != 0) {

            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logFaixaLocalidade.getLocNu()).append(", ");
            sql.append("'").append(escapeSpecial(logFaixaLocalidade.getLocCepIni())).append("', ");
            sql.append("'").append(escapeSpecial(logFaixaLocalidade.getLocCepFim())).append("', ");
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
