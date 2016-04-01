package org.droidplanner.services.android.core.drone.variables;

import com.MAVLink.enums.MAV_TYPE;

import java.util.ArrayList;
import java.util.List;

public enum PX4Modes implements IVehicleModes {

	ROTOR_STABILIZE(0, "Stabilize", MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_ACRO(1,"Acro", MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_ALT_HOLD(2, "Alt Hold",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_AUTO(3, "Auto",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_GUIDED(4, "Guided",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_LOITER(5, "Loiter",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_RTL(6, "RTL",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_CIRCLE(7, "Circle",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_LAND(9, "Land",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_TOY(11, "Drift",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_SPORT(13, "Sport",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_AUTOTUNE(15, "Autotune",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_POSHOLD(16, "PosHold",MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_BRAKE(17,"Brake",MAV_TYPE.MAV_TYPE_QUADROTOR),
    ROTOR_INDOOR_TAKEOFF(18,"ID TKOF",MAV_TYPE.MAV_TYPE_QUADROTOR),
    ROTOR_INDOOR_LANDING(19,"ID Land",MAV_TYPE.MAV_TYPE_QUADROTOR),

	UNKNOWN(-1, "Unknown", MAV_TYPE.MAV_TYPE_GENERIC);

	private final long number;
    private final String name;
	private final int type;

	PX4Modes(long number, String name, int type){
		this.number = number;
		this.name = name;
		this.type = type;
	}
// TODO: read this before start
//    ================ Method in QGC: ==================
//    refer to: QGC/src/FirmwarePlugin/PX4/PX4FirmwarePlugin.cc
//    union px4_custom_mode {
//        struct {
//            uint16_t reserved;
//            uint8_t main_mode;
//            uint8_t sub_mode;
//        };
//        uint32_t data;
//        float data_float;
//    };
//    union px4_custom_mode px4_mode;
//    px4_mode.data = 0;
//    px4_mode.main_mode = pModes2Name->main_mode;
//    px4_mode.sub_mode = pModes2Name->sub_mode;
//    *custom_mode = px4_mode.data;

    @Override
	public long getNumber() {
        return number; //TODO: change this to PX4 code, not just 1~17
	}

    @Override
	public String getName() {
		return name;
	}

    @Override
	public int getType() {
		return type;
	}

    @Override
	public PX4Modes getMode(long i, int type) {
        if (isCopter(type)) {
            type = MAV_TYPE.MAV_TYPE_QUADROTOR;
        }

		for (PX4Modes mode : PX4Modes.values()) {
			if (i == mode.getNumber() && type == mode.getType()) {
				return mode;
			}
		}
		return UNKNOWN;
	}

    @Override
	public PX4Modes getMode(String str, int type) {
        if (isCopter(type)) {
            type = MAV_TYPE.MAV_TYPE_QUADROTOR;
        }

		for (PX4Modes mode : PX4Modes.values()) {
			if (str.equals(mode.getName()) && type == mode.getType()) {
				return mode;
			}
		}
		return UNKNOWN;
	}

    @Override
	public List<IVehicleModes> getModeList(int type) {
		List<IVehicleModes> modeList = new ArrayList<IVehicleModes>();

		if (isCopter(type)) {
			type = MAV_TYPE.MAV_TYPE_QUADROTOR;
		}

		for (PX4Modes mode : PX4Modes.values()) {
			if (mode.getType() == type) {
				modeList.add(mode);
			}
		}
		return modeList;
	}

    @Override
	public boolean isValid(IVehicleModes mode) { return mode!= PX4Modes.UNKNOWN; }

    @Override
	public boolean isCopter(int type){
		switch (type) {
		case MAV_TYPE.MAV_TYPE_TRICOPTER:
		case MAV_TYPE.MAV_TYPE_QUADROTOR:
		case MAV_TYPE.MAV_TYPE_HEXAROTOR:
		case MAV_TYPE.MAV_TYPE_OCTOROTOR:
		case MAV_TYPE.MAV_TYPE_HELICOPTER:
			return true;

		default:
			return false;
		}
	}

}
