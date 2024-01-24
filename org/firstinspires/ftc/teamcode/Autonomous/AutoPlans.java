package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class AutoPlans extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}

/*      TO DO LIST IN AUTO                               IMPORTANT?
            - GET TEAM COLOR                                    *
            - GET POS (BACKSTAGE OR AUDIENCE)                   *
            - DETECT SPIKE MARK TAPE, KEEP POSITION             YES
            - PLACE PURPLE PIXEL ON SPIKE TAPE                  YES 10/20 PTS
            - GO INTO BACKSTAGE (IF NOT IN BACKSTAGE)           YES
            - PLACE YELLOW PIXEL ON BACKDROP WITH SPIKE POS     YES 10/20 PTS
            - CYCLE WHITE PIXELS (IF APPLICABLE)                POINTS VARY
            - PARK IN BACKDROP                                  YES 5 PTS
            - WAIT FOR TELEOP                                   CYCLE BACKDROP 5 PTS BACKSTAGE 3 PTS

            MAX POINTS IN AUTO, C IS CYCLE PIXELS
                NO TEAM PROP - 10 + 10 + 5 + 5 + 5C = 30+5C
                TEAM PROP - 20 + 20 + 5 + 5 + 5C = 50+5C

            CURRENT AUTO PLANS
                PARK IN BACKDROP (DONE LM2)
                POINTS: 5
                DETECT SPIKE, NO PURPLE PIXEL (DONE 1/2/24)
                POINTS:
                YELLOW PIXEL IN BACKDROP
                POINTS: 10/20

                PLANS 12/19
                - PIXEL STACK REMOVER
                - REDESIGN TRANSFER
                - WORK ON APRILTAGS
                - RE-TAPE FIELD

                UPDATE 1/2/24
                - CAN DETECT TEAM PROP
                - OPENCV IS BROKEN

*/