package com.sevenwonders.managment.application.card.selectedcard;

import com.sevenwonders.management.domain.card.entities.Construction;
import com.sevenwonders.management.domain.card.values.Chained;
import com.sevenwonders.management.domain.card.values.Effect;
import com.sevenwonders.management.domain.card.values.Shields;
import com.sevenwonders.management.domain.card.values.Status;
import com.sevenwonders.managment.application.shared.ports.IEventsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class SelectedCardUseCaseTest {

  private final SelectedCardUseCase useCase;
  private final IEventsRepositoryPort repository;

  public SelectedCardUseCaseTest() {
    repository = Mockito.mock(IEventsRepositoryPort.class);
    useCase = new SelectedCardUseCase(repository);
  }


//  @Test
//  void executeSuccess() {
//    SelectedCardRequest request = new SelectedCardRequest(
//      "BATHS",
//      1,
//      "CIVIL",
//      "BLUE",
//      new Construction(
//        Status.of("INPROGRESS"),
//        Chained.of(false),
//        Shields.of(0),
//        Effect.of("NONE")
//      ),
////    new Requirement(
////      RequirementId.of("R1"),
////      Amount.of(3),
////      Resources.of(List.of("IRON")),
////      MinimumPlayers.of(4)
////    )
////    );
//    StepVerifier
//      .create(useCase.execute(request))
//      .assertNext(response -> {
//        assertNotNull(response);
//        assertEquals("BATHS", response.getCardName());
//        assertEquals(1, response.getEra());
//        assertEquals("CIVIL", response.getType());
//        assertEquals("BLUE", response.getColor());
//        assertNotNull(response.getConstruction());
//        assertEquals("INPROGRESS", response.getConstruction().getStatus());
//        assertFalse(response.getConstruction().getChained());
//
//      })
//      .verifyComplete();
//
//  }
}
