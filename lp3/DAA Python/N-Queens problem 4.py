def is_safe(board, row, col, n):
    # Check this column on upper side
    for i in range(row):
        if board[i][col] == 1:
            return False

    # Check upper diagonal on left side
    for i, j in zip(range(row, -1, -1), range(col, -1, -1)):
        if board[i][j] == 1:
            return False

    # Check upper diagonal on right side
    for i, j in zip(range(row, -1, -1), range(col, n)):
        if board[i][j] == 1:
            return False

    return True

def solve_n_queens_util(board, row, n):
    # Base case: If all queens are placed
    if row >= n:
        return True

    for col in range(n):
        if is_safe(board, row, col, n):
            board[row][col] = 1  # Place queen

            if solve_n_queens_util(board, row + 1, n):
                return True

            # If placing queen doesn't lead to a solution, remove it
            board[row][col] = 0

    return False

def print_board(board, n):
    for row in range(n):
        print(" ".join("Q" if board[row][col] == 1 else "." for col in range(n)))

def solve_n_queens(n, first_queen_row, first_queen_col):
    # Create the board
    board = [[0 for _ in range(n)] for _ in range(n)]
    
    # Place the first queen
    board[first_queen_row][first_queen_col] = 1

    # Start solving for the next queen
    if not solve_n_queens_util(board, 1, n):
        print("No solution exists!")
    else:
        print_board(board, n)

if __name__ == "__main__":
    n = int(input("Enter the number of queens (N): "))
    first_queen_row = int(input("Enter the row for the first queen (0-indexed): "))
    first_queen_col = int(input("Enter the column for the first queen (0-indexed): "))

    # Validate input
    if not (0 <= first_queen_row < n) or not (0 <= first_queen_col < n):
        print("Invalid position for the first queen!")
    else:
        solve_n_queens(n, first_queen_row, first_queen_col)
