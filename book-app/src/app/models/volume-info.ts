import { ImageLinks } from './image-links';
import { IndustryIdentifier } from './industry-identifier';

export interface VolumeInfo {
  title: string;
  authors: Array<string>;
  publisher: string;
  publishedDate: Date;
  description: string;
  industryIdentifiers: Array<IndustryIdentifier>;
  pageCount: number;
  categories: Array<string>;
  averageRating: string;
  ratingsCount: number;
  imageLinks: ImageLinks;
  previewLink: string;
  language: string;
}
