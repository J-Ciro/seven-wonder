package com.sevenwonders.management.domain.card;

import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.entities.Requirement;
import com.sevenwonders.management.domain.card.events.CheckedConstruction;
import com.sevenwonders.management.domain.card.events.CheckedRequirement;
import com.sevenwonders.management.domain.card.events.DiscardedCard;
import com.sevenwonders.management.domain.card.events.DiscardedConstruction;
import com.sevenwonders.management.domain.card.events.SelectedCard;
import com.sevenwonders.management.domain.card.events.UpdatedRequirement;
import com.sevenwonders.management.domain.card.events.ValidatedConstruction;
import com.sevenwonders.management.domain.card.events.ValidatedRequirement;
import com.sevenwonders.management.domain.card.values.CardId;
import com.sevenwonders.management.domain.card.values.Color;
import com.sevenwonders.management.domain.card.values.Era;
import com.sevenwonders.management.domain.card.values.Name;
import com.sevenwonders.management.domain.card.values.Type;
import com.sevenwonders.shared.domain.generic.AggregateRoot;
import com.sevenwonders.shared.domain.generic.DomainEvent;

import java.util.List;

public class Card extends AggregateRoot<CardId> {


  private Name name;
  private Era era;
  private Type type;
  private Color color;
  private Construction construction;
  private Requirement requirement;


//  //region Constructors
//  public Card( String cardName, Integer era, String type, String color, Construction construction, Requirement requirement) {
//    super(new CardId());
//    subscribe(new CardHandler(this));
//    apply(new SelectedCard( cardName, era, type, color, construction , requirement.getResource().getValue(), requirement.getAmount().getValue(), requirement.getMinimumPlayers().getValue()));
//  }

  public Card(String cardName, Integer era, String type, String color, Construction construction, Requirement requirement){
    super(new CardId());
    subscribe(new CardHandler(this));
    apply(new SelectedCard( cardName, era, type, color, construction , requirement));
  }

  private Card(CardId identity) {
    super(identity);
    subscribe(new CardHandler(this));
  }

  //endregion

  //region Getters and Setters

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Era getEra() {
    return era;
  }

  public void setEra(Era era) {
    this.era = era;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Construction getConstruction() {
    return construction;
  }

  public void setConstruction(Construction construction) {
    this.construction = construction;
  }

  public Requirement getRequirement() {
    return requirement;
  }

  public void setRequirement(Requirement requirement) {
    this.requirement = requirement;
  }


  //endregion

  //region Domain Actions
public void selectedCard( String cardName, Integer era, String type, String color, Integer amount, List<String> resources, Integer minimumPlayers, Construction constructions){
//  apply(new SelectedCard( cardName, era, type, color, constructions, resources, amount, minimumPlayers));
  apply(new SelectedCard( cardName, era, type, color, construction, requirement));
}

public void discardedCard(String id, String name, Integer era, String type, String color, Requirement requirements, Construction constructions){
  apply(new DiscardedCard(id, name, era, type, color, requirements, constructions));
}

public void checkRequirement(String id, Integer price, List<String> resources, Integer minimumPlayers) {
  apply(new CheckedRequirement(id, price, resources, minimumPlayers));
}

public void updateRequirement(String id, Integer price, List<String> resources, Integer minimumPlayers) {
    apply(new UpdatedRequirement(id, price, resources, minimumPlayers));
}

public void validateRequirement(String id, Integer price, List<String> resources, Integer minimumPlayers) {
  apply(new ValidatedRequirement(id, price, resources, minimumPlayers));
}

public void discardConstruction(String id, String status, Boolean chained, Integer shields, String effect) {
  apply(new DiscardedConstruction(id, status, chained, shields, effect));
}

public void validateConstruction(String id, String status, Boolean chained, Integer shields, String effect) {
  apply(new ValidatedConstruction(id, status, chained, shields, effect));
}

public void checkConstruction(String id, String status, Boolean chained, Integer shields, String effect) {
  apply(new CheckedConstruction(id, status, chained, shields, effect));
}

  //endregion

  public static Card from(final String identity, final List<DomainEvent> events){

    Card card = new Card(CardId.of(identity));
    events.forEach(card::apply);

    return card;

  }

}
