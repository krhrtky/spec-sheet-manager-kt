import { storiesOf } from "@storybook/vue";
import HelloWorld from "../components/HelloWorld";

storiesOf('HelloWorld', module).add('simple', () => ({
  components: { HelloWorld },
  template: "<hello-world/>"
}))
