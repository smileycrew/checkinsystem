export default function GuestItem({ guest, index }) {
  return (
    <li className="border-b grid grid-cols-2">
      <p>{index}.</p>
      <p className="text-end">{guest.fullName}</p>
    </li>
  )
}
