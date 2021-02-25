package ca.webber.ftc.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.vision.UGContourRingPipeline;
import com.arcrobotics.ftclib.vision.UGContourRingPipeline.Height;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class Vision {
    OpenCvCamera camera;
    UGContourRingPipeline visionPipeLine;

    public Vision(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "camera"), cameraMonitorViewId);

        visionPipeLine = new UGContourRingPipeline();

        camera.setPipeline(visionPipeLine);

        camera.openCameraDeviceAsync(() -> {
            camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
        });

        FtcDashboard.getInstance().startCameraStream(camera, 30);
    }

    public Height getHeight() {
        return visionPipeLine.getHeight();
    }

    public void stop() {
        camera.stopStreaming();
        camera.closeCameraDevice();
    }
}
