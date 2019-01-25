package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogLogradouro;

/**
 * Classe de conversão de layout do arquivo de LOG_LOGRADOURO de CEP.
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:03:00
 */
public class LogLogradouroConverter implements Converter<LogLogradouro, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_LOGRADOURO";
    private static final String columns = "LOG_NU, UFE_SG, LOC_NU, BAI_NU_INI, BAI_NU_FIM, LOG_NO, LOG_COMPLEMENTO, "
            + "CEP, TLO_TX, LOG_STA_TLO, LOG_NO_ABREV, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;
    private String auxiliar;

    @Override
    public LogLogradouro convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogLogradouro logLogradouro = new LogLogradouro();

        logLogradouro.setLogNu(getNumber(splited, 0));
        logLogradouro.setUfeSg(getString(splited, 1));
        logLogradouro.setLocNu(getNumber(splited, 2));
        logLogradouro.setBaiNuIni(getNumber(splited, 3));
        logLogradouro.setBaiNuFim(
                getNumber(splited, 4) != 0 ? getNumber(splited, 4) : null);
        logLogradouro.setLogNo(getString(splited, 5));
        logLogradouro.setLogComplemento(getString(splited, 6));
        logLogradouro.setCep(getString(splited, 7));
        logLogradouro.setTloTx(getString(splited, 8));
        logLogradouro.setLogStaTlo(getString(splited, 9));
        logLogradouro.setLogNoAbrev(getString(splited, 10));

        return logLogradouro;
    }

    @Override
    public StringBuilder generateSQL(LogLogradouro logLogradouro) {

        StringBuilder sql = new StringBuilder();

        if (contador == 0) {
            auxiliar = " WHERE ufe_sg = '" + logLogradouro.getUfeSg() + "' ";
        }

        contador++;

        if (logLogradouro.getLogNu() != 0) {
            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logLogradouro.getLogNu()).append(", ");
            sql.append("'").append(escapeSpecial(logLogradouro.getUfeSg())).append("', ");
            sql.append(logLogradouro.getLocNu()).append(", ");
            sql.append(logLogradouro.getBaiNuIni()).append(", ");
            sql.append(logLogradouro.getBaiNuFim()).append(", ");
            sql.append("'").append(escapeSpecial(logLogradouro.getLogNo())).append("', ");
            sql.append("'").append(escapeSpecial(logLogradouro.getLogComplemento())).append("', ");
            sql.append("'").append(escapeSpecial(logLogradouro.getCep())).append("', ");
            sql.append("'").append(escapeSpecial(logLogradouro.getTloTx())).append("', ");
            sql.append("'").append(escapeSpecial(logLogradouro.getLogStaTlo())).append("', ");
            sql.append("SUBSTR('").append(escapeSpecial(logLogradouro.getLogNoAbrev().trim())).append("', 0,36), ");
            sql.append("USER, ");
            sql.append("current_timestamp");

            SqlGenerateUtil.insertFinish(sql);
        }

        if ((contador % numCommits) == 0) SqlGenerateUtil.commit(sql);

        return sql;
    }

    @Override
    public void countRegistros(StringBuilder stringBuilder) {

        SqlGenerateUtil.countRegistrosInseridos(stringBuilder, esquema, table, auxiliar);
    }
}
