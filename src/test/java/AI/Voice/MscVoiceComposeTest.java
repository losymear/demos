package AI.Voice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class MscVoiceComposeTest {

    @Test
    void TestForJunit5(){
        MscVoiceCompose mscVoiceCompose = new MscVoiceCompose();
        assertTrue(mscVoiceCompose.tmp()=="tmp");
    }

}