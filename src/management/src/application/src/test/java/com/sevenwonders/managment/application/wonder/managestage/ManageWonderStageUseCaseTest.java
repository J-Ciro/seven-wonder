package com.sevenwonders.managment.application.wonder.managestage;

import com.sevenwonders.management.domain.wonder.events.AssignedWonder;
import com.sevenwonders.managment.application.shared.repositories.IEventsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManageWonderStageUseCaseTest {

  private final ManageWonderStageUseCase useCase;
  private final IEventsRepository repository;

  public ManageWonderStageUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new ManageWonderStageUseCase(repository);
  }


  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregatedId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new AssignedWonder("Pyramid", "NIGHT")
      ));

    ManageWonderStageRequest request = new ManageWonderStageRequest("123", "Pyramid", "ERA 1", 5, List.of("Wood", "Stone"));

    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals("123", response.getWonderId());
        assertEquals("Pyramid", response.getWonderName());
        assertEquals("ERA 1", response.getStage().getName());
        assertNotNull(response.getStage().getName());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregatedId(Mockito.anyString());
  }
}