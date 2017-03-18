export default function (price) {
  const s = price.toString();
  return '$ ' + s.slice(0, -2) + '.' + s.slice(-2);
}