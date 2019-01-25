package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogNumSec;

/**
 * Classe de conversão de layout do arquivo de LOG_NUM_SEC de CEP.
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:03:00
 */
public class LogNumSecConverter implements Converter<LogNumSec, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_NUM_SEC";
    private static final String columns = "LOG_NU, SEC_NU_INI, SEC_NU_FIM, SEC_IN_LADO, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogNumSec convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogNumSec logNumSec = new LogNumSec();

        logNumSec.setLogNu(getNumber(splited, 0));
        logNumSec.setSecNuIni(getString(splited, 1));
        logNumSec.setSecNuFim(getString(splited, 2));
        logNumSec.setSecInLado(getString(splited, 3));

        return logNumSec;
    }

    @Override
    public StringBuilder generateSQL(LogNumSec logNumSec) {

        StringBuilder sql = new StringBuilder();

        contador++;

        if (logNumSec.getLogNu() != 0) {
            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logNumSec.getLogNu()).append(", ");
            sql.append("'").append(escapeSpecial(logNumSec.getSecNuIni())).append("', ");
            sql.append("'").append(escapeSpecial(logNumSec.getSecNuFim())).append("', ");
            sql.append("'").append(escapeSpecial(logNumSec.getSecInLado())).append("', ");
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
