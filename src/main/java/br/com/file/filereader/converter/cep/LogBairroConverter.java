package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogBairro;


/**
 * Classe de conversão de layout do arquivo de LOG_BAIRRO de CEP.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:00:00
 */
public class LogBairroConverter implements Converter<LogBairro, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_BAIRRO";
    private static final String columns = "BAI_NU, UFE_SG, LOC_NU, BAI_NO, BAI_NO_ABREV, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogBairro convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogBairro logBairro = new LogBairro();

        logBairro.setBaiNu(getNumber(splited, 0));
        logBairro.setUfeSg(getString(splited, 1));
        logBairro.setLocNu(getNumber(splited, 2));
        logBairro.setBaiNo(getString(splited, 3));
        logBairro.setBaiNoAbrev(getString(splited, 4));

        return logBairro;
    }

    @Override
    public StringBuilder generateSQL(LogBairro logBairro) {

        StringBuilder sql = new StringBuilder();

        contador++;

        if (logBairro.getBaiNu() != 0) {

            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logBairro.getBaiNu()).append(", ");
            sql.append("'").append(escapeSpecial(logBairro.getUfeSg())).append("', ");
            sql.append(logBairro.getLocNu()).append(", ");
            sql.append("'").append(escapeSpecial(logBairro.getBaiNo())).append("', ");
            sql.append("'").append(escapeSpecial(logBairro.getBaiNoAbrev())).append("', ");
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
