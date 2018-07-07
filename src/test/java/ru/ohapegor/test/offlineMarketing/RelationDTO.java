package ru.ohapegor.test.offlineMarketing;

/**
 * Шаблон для данных об одном поле фильтра со фронта
 */
public class RelationDTO {
    /** Поле или соединитель */
    private boolean field;
    /** Имя поля */
    private String operandA;
    /** Тип содержимого поля */
    private OperandType operandType;
    /** Значения для условия или тип соединителя */
    private String operandB;
    /** Условие для поля */
    private String relation;

    public RelationDTO() {}

    /** Конструктор для поля */
    public RelationDTO(String operandA, OperandType operandType, String operandB, String relation) {
        this.field = true;
        this.operandA = operandA;
        this.operandType = operandType;
        this.operandB = operandB;
        this.relation = relation;
    }

    /** Конструктор для соединителя */
    public RelationDTO(String operandB) {
        this.operandB = operandB;
    }

    public boolean isField() {
      return field;
    }

    public void setField(boolean field) {
      this.field = field;
    }

    public String getOperandA() {
      return operandA;
    }

    public void setOperandA(String operandA) {
      this.operandA = operandA;
    }

    public OperandType getOperandType() {
      return operandType;
    }

    public void setOperandType(OperandType operandType) {
      this.operandType = operandType;
    }

    public String getOperandB() {
      return operandB;
    }

    public void setOperandB(String operandB) {
      this.operandB = operandB;
    }

    public String getRelation() {
      return relation;
    }

    public void setRelation(String relation) {
      this.relation = relation;
    }
}
