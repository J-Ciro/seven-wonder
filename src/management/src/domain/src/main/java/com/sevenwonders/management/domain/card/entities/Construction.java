package com.sevenwonders.management.domain.card.entities;

import com.sevenwonders.management.domain.card.values.Chained;
import com.sevenwonders.management.domain.card.values.ConstructionId;

import com.sevenwonders.management.domain.card.values.Effect;
import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.shared.domain.generic.Entity;

public class Construction extends Entity<ConstructionId> {

  private Status status;
  private Chained chained;
  private Shields shields;
  private Effect effect;


  public Construction(Status status, Chained chained, Shields shields, Effect effect) {
    super(new ConstructionId());
    this.status = status;
    this.chained = chained;
    this.shields = shields;
    this.effect = effect;
  }


  public Construction(ConstructionId identity, Status status, Chained chained, Shields shields, Effect effect) {
    super(identity);
    this.status = status;
    this.chained = chained;
    this.shields = shields;
    this.effect = effect;
  }


  public Integer calculateShields() {
    return shields.getValue();
  }


  public Integer checkShields(Integer enemyShields, Integer currentEra ) {
    Integer shield = calculateShields();
    if (shield > enemyShields) {
      return switch (currentEra) {
        case 1 -> 1;
        case 2 -> 3;
        case 3 -> 5;
        default -> throw new IllegalArgumentException("Era inválida: " + currentEra);
      };
    }
    return Integer.compare(shield, enemyShields);
  }


  public String updateEffect(String effect) {
    return effect;
  }


  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Chained getChained() {
    return chained;
  }

  public void setChained(Chained chained) {
    this.chained = chained;
  }

  public Shields getShields() {
    return shields;
  }

  public void setShields(Shields shields) {
    this.shields = shields;
  }

  public Effect getEffect() {
    return effect;
  }

  public void setEffect(Effect effect) {
    this.effect = effect;
  }
}
