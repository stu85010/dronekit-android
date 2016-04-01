package org.droidplanner.services.android.core.drone.variables;

import com.MAVLink.enums.MAV_TYPE;

import java.util.ArrayList;
import java.util.List;

public interface IVehicleModes {
	long getNumber();
	String getName();
	int getType();
	IVehicleModes getMode(long i, int type);
	IVehicleModes getMode(String str, int type);
	List<IVehicleModes> getModeList(int type);
	boolean isValid(IVehicleModes mode);
	boolean isCopter(int type);
}
