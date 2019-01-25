package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogUnidOper;

/**
 * Classe de conversão de layout do arquivo de LOG_UNID_OPER de CEP.
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:03:00
 */
public class LogUnidOperConverter implements Converter<LogUnidOper, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_UNID_OPER";
    private static final String columns = "UOP_NU, UFE_SG, LOC_NU, BAI_NU, LOG_NU, UOP_NO, UOP_ENDERECO, CEP, "
            + "UOP_IN_CP, UOP_NO_ABREV, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogUnidOper convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogUnidOper logUnidOper = new LogUnidOper();

        logUnidOper.setUopNu(getNumber(splited, 0));
        logUnidOper.setUfeSg(getString(splited, 1));
        logUnidOper.setLocNu(getNumber(splited, 2));
        logUnidOper.setBaiNu(getNumber(splited, 3));
        logUnidOper.setLogNu(
                getNumber(splited, 4) != 0 ? getNumber(splited, 4) : null);
        logUnidOper.setUopNo(getString(splited, 5));
        logUnidOper.setUopEndereco(getString(splited, 6));
        logUnidOper.setCep(getString(splited, 7));
        logUnidOper.setUopInCp(getString(splited, 8));
        logUnidOper.setUopNoAbrev(getString(splited, 9));


        return logUnidOper;
    }

    @Override
    public StringBuilder generateSQL(LogUnidOper logUnidOper) {

        StringBuilder sql = new StringBuilder();

        contador++;

        if (logUnidOper.getUopNu() != 0) {
            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logUnidOper.getUopNu()).append(", ");
            sql.append("'").append(escapeSpecial(logUnidOper.getUfeSg())).append("', ");
            sql.append(logUnidOper.getLocNu()).append(", ");
            sql.append(logUnidOper.getBaiNu()).append(", ");
            sql.append(logUnidOper.getLogNu()).append(", ");
            sql.append("'").append(escapeSpecial(logUnidOper.getUopNo())).append("', ");
            sql.append("'").append(escapeSpecial(logUnidOper.getUopEndereco())).append("', ");
            sql.append("'").append(escapeSpecial(logUnidOper.getCep())).append("', ");
            sql.append("'").append(escapeSpecial(logUnidOper.getUopInCp())).append("', ");
            sql.append("SUBSTR('").append(escapeSpecial(logUnidOper.getUopNoAbrev())).append("', 0,36), ");
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
