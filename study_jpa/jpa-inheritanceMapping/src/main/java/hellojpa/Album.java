package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // DTYPE 컬럼 값(Default : Entity명)
public class Album extends Item {
    private String artist;
}