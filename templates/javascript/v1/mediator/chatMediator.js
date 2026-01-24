export class ChatMediator {
  constructor() {
    this.participants = new Map();
  }

  register(participant) {
    this.participants.set(participant.id, participant);
  }

  broadcast(senderId, message) {
    for (const [id, participant] of this.participants.entries()) {
      if (id !== senderId) {
        console.log(participant.onMessage(senderId, message));
      }
    }
  }
}

export class Participant {
  constructor(id, mediator) {
    this.id = id;
    this.mediator = mediator;
  }

  send(message) {
    this.mediator.broadcast(this.id, message);
  }

  onMessage(_fromId, _message) {
    throw new Error("Not implemented");
  }
}

export class UserParticipant extends Participant {
  onMessage(fromId, message) {
    return `User ${this.id} received from ${fromId}: ${message}`;
  }
}
