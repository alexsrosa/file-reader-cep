package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogFaixaBai;


/**
 * Classe de conversão de layout do arquivo de LOG_FAIXA_BAI de CEP.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:00:00
 */
public class LogFaixaBaiConverter implements Converter<LogFaixaBai, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_FAIXA_BAI";
    private static final String columns = "BAI_NU, FCB_CEP_INI, FCB_CEP_FIM, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogFaixaBai convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogFaixaBai logfaixaBai = new LogFaixaBai();

        logfaixaBai.setBaiNu(getNumber(splited, 0));
        logfaixaBai.setFcbCepIni(getString(splited, 1));
        logfaixaBai.setFcbCepFim(getString(splited, 2));

        return logfaixaBai;
    }

    @Override
    public StringBuilder generateSQL(LogFaixaBai logfaixaBai) {

        StringBuilder sql = new StringBuilder();

        if (contador == 0) SqlGenerateUtil.deleteAllAndCommit(sql, esquema, table, "");

        contador++;

        if (logfaixaBai.getBaiNu() != 0) {

            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logfaixaBai.getBaiNu()).append(", ");
            sql.append("'").append(escapeSpecial(logfaixaBai.getFcbCepIni())).append("', ");
            sql.append("'").append(escapeSpecial(logfaixaBai.getFcbCepFim())).append("', ");
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
