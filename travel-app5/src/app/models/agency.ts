import { Ville } from './ville';

export class Agency {
    id: number;
    name: string;
    creationDate: Date;
    nbreBus: number;

    ville = new Ville();
}
