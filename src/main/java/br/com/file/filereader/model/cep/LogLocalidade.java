package br.com.file.filereader.model.cep;

public class LogLocalidade {

  private Long locNu;
  private String ufeSg;
  private String locNo;
  private String cep;
  private String locInSit;
  private String locInTipoLoc;
  private Long locNuSub;
  private String locNoAbrev;
  private Long munNu;
  private String usuarioInsercao;
  private java.sql.Timestamp dataInsercao;
  private String usuarioAlteracao;
  private java.sql.Timestamp dataAlteracao;
  private String registroExcluido;
  private String usuarioExclusao;
  private java.sql.Timestamp dataExclusao;


  public Long getLocNu() {
    return locNu;
  }

  public void setLocNu(Long locNu) {
    this.locNu = locNu;
  }


  public String getUfeSg() {
    return ufeSg;
  }

  public void setUfeSg(String ufeSg) {
    this.ufeSg = ufeSg;
  }


  public String getLocNo() {
    return locNo;
  }

  public void setLocNo(String locNo) {
    this.locNo = locNo;
  }


  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }


  public String getLocInSit() {
    return locInSit;
  }

  public void setLocInSit(String locInSit) {
    this.locInSit = locInSit;
  }


  public String getLocInTipoLoc() {
    return locInTipoLoc;
  }

  public void setLocInTipoLoc(String locInTipoLoc) {
    this.locInTipoLoc = locInTipoLoc;
  }


  public Long getLocNuSub() {
    return locNuSub;
  }

  public void setLocNuSub(Long locNuSub) {
    this.locNuSub = locNuSub;
  }


  public String getLocNoAbrev() {
    return locNoAbrev;
  }

  public void setLocNoAbrev(String locNoAbrev) {
    this.locNoAbrev = locNoAbrev;
  }


  public Long getMunNu() {
    return munNu;
  }

  public void setMunNu(Long munNu) {
    this.munNu = munNu;
  }

  public String getUsuarioInsercao() {
    return usuarioInsercao;
  }

  public void setUsuarioInsercao(String usuarioInsercao) {
    this.usuarioInsercao = usuarioInsercao;
  }


  public java.sql.Timestamp getDataInsercao() {
    return dataInsercao;
  }

  public void setDataInsercao(java.sql.Timestamp dataInsercao) {
    this.dataInsercao = dataInsercao;
  }


  public String getUsuarioAlteracao() {
    return usuarioAlteracao;
  }

  public void setUsuarioAlteracao(String usuarioAlteracao) {
    this.usuarioAlteracao = usuarioAlteracao;
  }


  public java.sql.Timestamp getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(java.sql.Timestamp dataAlteracao) {
    this.dataAlteracao = dataAlteracao;
  }


  public String getRegistroExcluido() {
    return registroExcluido;
  }

  public void setRegistroExcluido(String registroExcluido) {
    this.registroExcluido = registroExcluido;
  }


  public String getUsuarioExclusao() {
    return usuarioExclusao;
  }

  public void setUsuarioExclusao(String usuarioExclusao) {
    this.usuarioExclusao = usuarioExclusao;
  }


  public java.sql.Timestamp getDataExclusao() {
    return dataExclusao;
  }

  public void setDataExclusao(java.sql.Timestamp dataExclusao) {
    this.dataExclusao = dataExclusao;
  }

  @Override
  public String toString() {
    return "LogLocalidade{"
            + "locNu='" + locNu + '\''
            + ", ufeSg='" + ufeSg + '\''
            + ", locNo='" + locNo + '\''
            + ", cep='" + cep + '\''
            + ", locInSit='" + locInSit + '\''
            + ", locInTipoLoc='" + locInTipoLoc + '\''
            + ", locNuSub='" + locNuSub + '\''
            + ", locNoAbrev='" + locNoAbrev + '\''
            + ", munNu='" + munNu + '\''
            + '}';
  }

}
