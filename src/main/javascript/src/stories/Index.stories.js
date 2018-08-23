/* eslint-disable import/no-extraneous-dependencies */
import { storiesOf } from '@storybook/vue';
import Index from "../components/Index";

storiesOf('Index', module)
  .add('default', () => ({
    components: { Index },
    template: '<index></index>',
  }))
