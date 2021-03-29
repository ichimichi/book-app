import { Book } from './book';

export interface Recommendation {
  id: string;
  userId: string;
  books: Array<Book>;
}
