package berlin.tu.dbe.exitroom.env.things.effect;

/**
 * a thing instantiated for the ExitRoom in reaction to some user action.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class BloodDrops {

    private final String[] prints = {"Hmm, there's blood.", "I hope there's no vampire around.",
            "Ouh, I almost slipped on it, that was close.", "Bloody hell!", "I can't see blood *fainting*.",
            "Why should I look at that? I know what it is.", "Damn roses, my finger still hurts."};

    public void lookAt() {
        System.out.println(prints[(int) (Math.random() * prints.length)]);
    }
}
