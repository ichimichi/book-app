import { VolumeInfo } from './volume-info';

export interface Book {
  id: string;
  kind: string;
  volumeInfo: VolumeInfo;
}
