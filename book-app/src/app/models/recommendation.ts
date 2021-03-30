import { Book } from './book';

export interface Recommendation {
  id: string;
  bookId: string;
  users: Array<string>;
  book: Book;
}
