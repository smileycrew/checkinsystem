import GuestItem from "./GuestItem"

export default function GuestList({ guests }) {
  return (
    <ul>
      <li className="grid grid-cols-2">
        <p>No.</p>
        <p className="text-end">Guest</p>
      </li>
      {guests.map((guest, index) => (
        <GuestItem guest={guest} index={index + 1} key={guest.id} />
      ))}
    </ul>
  )
}
