package domain;

import java.io.Serializable;

public abstract class OpstiDomenskiObjekat implements Serializable {
    public abstract String getTableName();
	public abstract String getColumns();
	public abstract String getValues();
	public abstract String getCondition();
}
