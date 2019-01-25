package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogLocalidade;

/**
 * Classe de conversão de layout do arquivo de LOG_LOCALIDADE de CEP.
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:03:00
 */
public class LogLocalidadeConverter implements Converter<LogLocalidade, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_LOCALIDADE";
    private static final String columns = "LOC_NU, UFE_SG, LOC_NO, CEP, LOC_IN_SIT, "
            + "LOC_IN_TIPO_LOC, LOC_NU_SUB, LOC_NO_ABREV, MUN_NU, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogLocalidade convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogLocalidade logLocalidade = new LogLocalidade();

        logLocalidade.setLocNu(getNumber(splited, 0));
        logLocalidade.setUfeSg(getString(splited, 1));
        logLocalidade.setLocNo(getString(splited, 2));
        logLocalidade.setCep(getString(splited, 3));
        logLocalidade.setLocInSit(getString(splited, 4));
        logLocalidade.setLocInTipoLoc(getString(splited, 5));
        logLocalidade.setLocNuSub(getNumber(splited, 6));
        logLocalidade.setLocNoAbrev(getString(splited, 7));
        logLocalidade.setMunNu(getNumber(splited, 8));

        return logLocalidade;
    }

    @Override
    public StringBuilder generateSQL(LogLocalidade logLocalidade) {

        StringBuilder sql = new StringBuilder();

        contador++;

        if (logLocalidade.getLocNu() != 0) {
            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logLocalidade.getLocNu()).append(", ");
            sql.append("'").append(escapeSpecial(logLocalidade.getUfeSg())).append("', ");
            sql.append("'").append(escapeSpecial(logLocalidade.getLocNo())).append("', ");
            sql.append("'").append(escapeSpecial(logLocalidade.getCep())).append("', ");
            sql.append("'").append(escapeSpecial(logLocalidade.getLocInSit())).append("', ");
            sql.append("'").append(escapeSpecial(logLocalidade.getLocInTipoLoc())).append("', ");
            sql.append(logLocalidade.getLocNuSub()).append(", ");
            sql.append("'").append(escapeSpecial(logLocalidade.getLocNoAbrev())).append("', ");
            sql.append(logLocalidade.getMunNu()).append(", ");
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
