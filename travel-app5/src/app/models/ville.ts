import { Agency } from './agency';

export class Ville {
    id: number;

    name: string;

    agencys: Array<Agency> = new Array<Agency>();
}
