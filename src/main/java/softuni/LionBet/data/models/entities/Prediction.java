package softuni.LionBet.data.models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "predictions")
@Getter
@Setter
@NoArgsConstructor
public class Prediction extends BaseEntity{


}
