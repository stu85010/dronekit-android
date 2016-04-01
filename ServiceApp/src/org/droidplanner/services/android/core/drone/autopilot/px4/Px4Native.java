package org.droidplanner.services.android.core.drone.autopilot.px4;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.MAVLink.Messages.MAVLinkMessage;
import com.o3dr.services.android.lib.model.ICommandListener;

import org.droidplanner.services.android.communication.model.DataLink;
import org.droidplanner.services.android.core.MAVLink.MavLinkCommands;
import org.droidplanner.services.android.core.drone.LogMessageListener;
import org.droidplanner.services.android.core.drone.autopilot.generic.GenericMavLinkDrone;
import org.droidplanner.services.android.core.drone.variables.PX4Modes;
import org.droidplanner.services.android.core.firmware.FirmwareType;
import org.droidplanner.services.android.core.model.AutopilotWarningParser;

/**
 * Created by Fredia Huya-Kouadio on 9/10/15.
 */
public class Px4Native extends GenericMavLinkDrone {

    public Px4Native(String droneId, Context context, Handler handler, DataLink.DataLinkProvider<MAVLinkMessage> mavClient, AutopilotWarningParser warningParser, LogMessageListener logListener) {
        super(droneId, context, handler, mavClient, warningParser, logListener);
    }

    @Override
    public FirmwareType getFirmwareType() {
        return FirmwareType.PX4_NATIVE;
    }

    @Override
    protected boolean performTakeoff(Bundle data, ICommandListener listener) {
        MavLinkCommands.changeFlightMode(this, PX4Modes.ROTOR_INDOOR_TAKEOFF, listener);
        return true;
    }
}
