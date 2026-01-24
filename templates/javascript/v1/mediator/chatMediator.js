const NOT_IMPLEMENTED_ERROR = "Not implemented";
const USER_PREFIX = "User ";
const RECEIVED_INFIX = " received from ";
const MESSAGE_SEPARATOR = ": ";

function formatIncomingMessage(receiverId, senderId, message) {
  return `${USER_PREFIX}${receiverId}${RECEIVED_INFIX}${senderId}${MESSAGE_SEPARATOR}${message}`;
}

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
    throw new Error(NOT_IMPLEMENTED_ERROR);
  }
}

export class UserParticipant extends Participant {
  onMessage(fromId, message) {
    return formatIncomingMessage(this.id, fromId, message);
  }
}
